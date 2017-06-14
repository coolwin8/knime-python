package org.knime.python2.typeextension.builtin.zoneddatetime;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.knime.core.data.time.zoneddatetime.ZonedDateTimeValue;
import org.knime.python2.typeextension.Serializer;
import org.knime.python2.typeextension.SerializerFactory;

/**
 * Is used to serialize java8 ZonedTime objects to python datetime objects.
 * 
 * @author Clemens von Schwerin, KNIME.com, Konstanz, Germany
 */

public class ZonedDateTimeSerializerFactory extends SerializerFactory<ZonedDateTimeValue> {
	
	//public static final String SERIZALIZE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSxxx";
	//public static final String DESERIALIZE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSxxx'['VV']'";
	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSSxxx'['VV']'";
	
	public ZonedDateTimeSerializerFactory() {
		super(ZonedDateTimeValue.class);
	}
	
	@Override
	public Serializer<? extends ZonedDateTimeValue> createSerializer() {
		return new ZonedDateTimeSerializer();
	}
	
	private class ZonedDateTimeSerializer implements Serializer<ZonedDateTimeValue> {

		@Override
		public byte[] serialize(ZonedDateTimeValue value) throws IOException {
			ZonedDateTime date = value.getZonedDateTime();
			/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SERIZALIZE_FORMAT);
			String datestr = date.format(formatter);
			datestr += "[" + date.getZone() + "]"; */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
			String datestr = date.format(formatter);
			return datestr.getBytes("UTF-8");
		}
		
	}

}