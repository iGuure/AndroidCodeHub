/*

    需要权限
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    参考
    https://segmentfault.com/q/1010000002513172

*/

public class MainActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;

    public static final int CROP_PHOTO = 2;

//    public static final int CHOOSE_PHOTO = 3;

    private Button takePhoto;

    private ImageView picture;

    private Uri imageUri;

    private Button chooseFromAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建File对象，用于储存拍照后的图片
                File outputImage = new File(Environment.getExternalStorageDirectory(), "output.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序
            }
        });

        chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setType("image/*");
                startActivityForResult(intent, TAKE_PHOTO); // 打开相册
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imageUri = data.getData();
                    }
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
//            case CHOOSE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    // 判断手机系统版本号
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        // 4.4及以上系统使用这个方法处理图片
//                        handleImageOnKitKat(data);
//                    } else {
//                        // 4.4以下系统使用这个方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
            default:
                break;
        }
    }

//    @TargetApi(19)
//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            // 如果是document类型的Uri，则通过document id处理
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1]; // 解析出数字格式的id
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//                // 如果不是document类型的Uri,则使用普通方式处理
//                imagePath = getImagePath(uri, null);
//            }
//            displayImage(imagePath); // 根据图片路径显示图片
//        }
//    }
//
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        displayImage(imagePath);
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        // 通过Uri和selection来获得真实的图片路径
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }
//
//    private void displayImage(String imagePath) {
//        if (imagePath != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            picture.setImageBitmap(bitmap);
//        } else {
//            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
//        }
//    }
}
