package innocv.general.mapper;


import java.text.SimpleDateFormat;
import java.util.Date;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OrikaDateConverter extends BidirectionalConverter<Date, String> {
   
    private final String pattern ;
    
    public OrikaDateConverter(String format) {
        this.pattern = format;
    }

   
	@Override
	public String convertTo(Date source, Type<String> destinationType,MappingContext arg2) {
		if (source!=null){
			return new SimpleDateFormat(pattern).format(source);
		}else{
			return null;
		}
	}


	@Override
	public Date convertFrom(String source, Type<Date> destinationType, MappingContext mappingContext) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public Date convertFrom(String source, Type<Date> destinationType, MappingContext arg2) {
		return BlogUtil.StringToDate(pattern, source);
	}*/



}