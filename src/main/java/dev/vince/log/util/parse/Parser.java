package dev.vince.log.util.parse;

import dev.vince.log.MiniLog;
import dev.vince.log.text.api.AbstractTextFormat;

public final class Parser {
    private Parser(){}
    
    public static ParsingBean parse(final ParsingBean data){
        for(AbstractTextFormat type : MiniLog.getInstance().getFormattingCacheManager().getFormats()){
            data.setInput(data.getInput().replace(type.getKey(), type.getText(data)));
        }

        return data;
    }

    public static ParsingBean parse(final ParsingBean data, final String key){
        String input = key;
        for(AbstractTextFormat type : MiniLog.getInstance().getFormattingCacheManager().getFormats()){
            input = input.replace(type.getKey(), type.getText(data));
        }

        data.setInput(input);
        return data;
    }
}