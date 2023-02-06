package dev.vince.log.util;

import dev.vince.log.text.AbstractTextFormat;
import dev.vince.log.text.ParsingBean;
import dev.vince.log.text.TextFormattingEnum;

public final class StringParser {
    public static ParsingBean parse(final ParsingBean data){
        for(AbstractTextFormat type : TextFormattingEnum.FormattingCacheManager.getFormats()){
            data.setInput(data.getInput().replace(type.getKey(), type.getText(data)));
        }

        return data;
    }

    public static ParsingBean parse(final ParsingBean data, final String key){
        String input = key;
        for(AbstractTextFormat type : TextFormattingEnum.FormattingCacheManager.getFormats()){
            input = input.replace(type.getKey(), type.getText(data));
        }

        data.setInput(input);
        return data;
    }
}