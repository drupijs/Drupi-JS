package net.stacket.drupi.legacy.expansions.skript.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.stacket.drupi.legacy.MainPlugin;
import org.bukkit.event.Event;

public class expResultOfDrupiFunction extends SimpleExpression<Object> {

    private Expression<String> function;
    private Expression<Object> obj;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        function = (Expression<String>) expressions[0];
        if(expressions.length == 2) {
            obj = (Expression<Object>) expressions[1];
        }
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return null;
    }

    @Override
    protected Object[] get(Event event) {
        if(function == null) return null;

        String _function = function.getSingle(event);

        if(obj != null){
            return new Object[]{MainPlugin.drupi.callFunctionWithResult(_function, obj.getArray(event))};
        } else {
            return new Object[]{MainPlugin.drupi.callFunctionWithResult(_function)};
        }
    }
}
