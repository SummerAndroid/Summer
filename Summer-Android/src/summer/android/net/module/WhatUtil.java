package summer.android.net.module;

/**
 * 
 * ÿ�ζ�������Ψһ��Request��ʶ��what
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class WhatUtil {

	public static int what() {
		// ��hashCode����ײ��
		return Long.valueOf(System.currentTimeMillis()).hashCode();
	}
}