package hundeklemmen.minehut.expansions.skript.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import hundeklemmen.minehut.MainPlugin;
import org.bukkit.event.Event;

public class effCallDrupiFunction extends Effect {

    private Expression<String> function;
    private Expression<Object> obj;

    @Override
    public boolean init(Expression<?>[] expressions, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3){
        function = (Expression<String>) expressions[0];
        if(expressions.length == 2) {
            obj = (Expression<Object>) expressions[1];
        }
        return true;
    }

    @Override
    public String toString(Event arg0, boolean arg1){
        return null;
    }

    @Override
    protected void execute(Event event){
        if(function == null) return;

        String _function = function.getSingle(event);

        if(obj != null){
            MainPlugin.drupi.callFunction(_function, obj.getArray(event));
        } else {
            MainPlugin.drupi.callFunction(_function);
        }
    }
    //set {_response} to result of drupi function ""
    //call drupi function "test" with argument[s] x, x2 and x3
}
