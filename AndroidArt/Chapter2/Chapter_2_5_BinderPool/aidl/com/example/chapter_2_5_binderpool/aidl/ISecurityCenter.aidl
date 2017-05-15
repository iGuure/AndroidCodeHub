// ISecurityCenter.aidl
package com.example.chapter_2_5_binderpool.aidl;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
