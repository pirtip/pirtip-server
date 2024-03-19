package com.pirtip.pirtipserver.client.naver;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ScaleAdjustDeserializer extends JsonDeserializer<BigDecimal> {

	private static final String MAPX = "mapx";
	private static final String MAPY = "mapy";

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String valueAsString = p.getValueAsString();
		BigDecimal value = new BigDecimal(valueAsString);

		if (p.getCurrentName().equals(MAPX)) {
			return value.scaleByPowerOfTen(3 - value.precision());
		} else if (p.getCurrentName().equals(MAPY)) {
			return value.scaleByPowerOfTen(2 - value.precision());
		} else {
			return value;
		}
	}
}
