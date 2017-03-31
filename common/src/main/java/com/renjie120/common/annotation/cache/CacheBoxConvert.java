package com.renjie120.common.annotation.cache;

import com.alibaba.fastjson.TypeReference;
import com.renjie120.common.utils.JsonUtils;
import com.renjie120.common.utils.ZipUnZipUtils;

public enum CacheBoxConvert {

	String(new TypeReference<String>() {
	});

	public TypeReference type;

	CacheBoxConvert(TypeReference type) {
		this.type = type;
	}

	public <T> T convertTo(String json) {
		try {
			String unZipStr = ZipUnZipUtils.getInstance().unzipBase642String(
					json);
			return JsonUtils.parseJsonStr(unZipStr, type.getType());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String convertFrom(Object obj) {
		try {
			String jsonStr = JsonUtils.toJsonStr(obj);
			return ZipUnZipUtils.getInstance().zipString2Base64(jsonStr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
