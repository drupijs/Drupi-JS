package hundeklemmen.script;

import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import hundeklemmen.main;

import java.util.ArrayList;
import java.util.List;

public class formsManager {

    private main plugin;
    public formsManager(main plugin){
        this.plugin = plugin;
    }

    public ElementButton newButton(String text){
        text = text.replaceAll("§", "\u00A7");
        return new ElementButton(TextFormat.colorize(text));
    }
    public ElementButtonImageData newElementButtonImageData(String url){
        return(new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, url));
    }

    public ElementSlider newSlider(String text, int min, int max, int step, int defaultValue){
        text = text.replaceAll("§", "\u00A7");
        return new ElementSlider(text, (float) min, (float) max, (int) step, (float) defaultValue);
    }
    public ElementInput newInput(String text, String placeholder, String defaultText){
        text = text.replaceAll("§", "\u00A7");
        placeholder = placeholder.replaceAll("§", "\u00A7");
        defaultText = defaultText.replaceAll("§", "\u00A7");
        return new ElementInput(text, placeholder, defaultText);
    }
    public ElementLabel newLabel(String text){
        text = text.replaceAll("§", "\u00A7");
        return new ElementLabel(text);
    }

    public ArrayList<String> newStringList(){
        return new ArrayList<String>();
    }
    public ElementDropdown newDropdown(String text, List<String> options, int defaultOption){
        text = text.replaceAll("§", "\u00A7");
        return new ElementDropdown(text, options, defaultOption);
    }

    public ElementStepSlider newStepSlider(String text, List<String> steps, int defaultValue){
        text = text.replaceAll("§", "\u00A7");
        return new ElementStepSlider(text, steps, defaultValue);
    }

    public ElementToggle newToggle(String text, boolean defaultValue){
        text = text.replaceAll("§", "\u00A7");
        return new ElementToggle(text, defaultValue);
    }

    public FormWindowSimple newSimpleForm(String title, String description){
        title = title.replaceAll("§", "\u00A7");
        description = description.replaceAll("§", "\u00A7");
        return new FormWindowSimple(TextFormat.colorize(title), TextFormat.colorize(description));
    }

    public FormWindowCustom newCustomForm(String title){
        title = title.replaceAll("§", "\u00A7");
        return new FormWindowCustom(TextFormat.colorize(title));
    }

    public FormWindowModal newModalForm(String title, String content, String trueText, String falseText){
        title = title.replaceAll("§", "\u00A7");
        content = content.replaceAll("§", "\u00A7");
        trueText = trueText.replaceAll("§", "\u00A7");
        falseText = falseText.replaceAll("§", "\u00A7");
        return new FormWindowModal(TextFormat.colorize(title), TextFormat.colorize(content), TextFormat.colorize(trueText), TextFormat.colorize(falseText));
    }


}
