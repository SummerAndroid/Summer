package summer.android.net.module;

/**
 * 
 * 每次都能生成唯一的Request标识号what
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class WhatUtil {

	public static int what() {
		// 用hashCode会碰撞吗？
		return Long.valueOf(System.currentTimeMillis()).hashCode();
	}
}