package com.shanghaizhida.mykotlin

class UserBean(
        val createdAt:String,
        val objectId:String,
        val sessionToken:String,
        val updatedAt:String,
        val username:String,
        val email:String,
        val emailVerified:Boolean
){
    override fun toString(): String {
        return "UserBean(createdAt='$createdAt', objectId='$objectId', sessionToken='$sessionToken', updatedAt='$updatedAt', username='$username', email='$email', emailVerified=$emailVerified)"
    }
}