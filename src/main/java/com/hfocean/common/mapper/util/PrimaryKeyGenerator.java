package com.hfocean.common.mapper.util;

import java.util.UUID;

/**主键生成策略
 * Created by guan.sj on 2018/3/5
 */
public class PrimaryKeyGenerator {
	
	public static String generate(){
		return new ObjectId().toHexString();
	}

	public static String generateForNum(){
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {//有可能是hashCodeV = - hashCode}
			// 0 代表前面补充0
			// 4 代表长度为4
			// d 代表参数为正数型
			hashCodeV = - hashCodeV;
		}
		return String.format("%010d", hashCodeV);
	}

}
