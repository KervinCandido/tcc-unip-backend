package br.unip.cc.tcc.model.jpa.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimeOpenFireConverter implements AttributeConverter<LocalDateTime, String> {

	private static final ZoneId AMERICA_SAO_PAULO = ZoneId.of("America/Sao_Paulo");

	@Override
	public String convertToDatabaseColumn(LocalDateTime attribute) {
		if (attribute == null) return null;
		return String.valueOf(attribute.atZone(AMERICA_SAO_PAULO).toInstant().toEpochMilli());
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
		if (dbData == null) return null;
		Long milli = Long.valueOf(dbData);
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), AMERICA_SAO_PAULO);
	}

}
