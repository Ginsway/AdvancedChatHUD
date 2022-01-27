package io.github.darkkronicle.advancedchathud.tabs;

import io.github.darkkronicle.Konstruct.functions.Function;
import io.github.darkkronicle.Konstruct.functions.ObjectFunction;
import io.github.darkkronicle.Konstruct.nodes.Node;
import io.github.darkkronicle.Konstruct.parser.IntRange;
import io.github.darkkronicle.Konstruct.parser.ParseContext;
import io.github.darkkronicle.Konstruct.parser.Result;
import io.github.darkkronicle.Konstruct.type.IntegerObject;
import io.github.darkkronicle.Konstruct.type.KonstructObject;
import io.github.darkkronicle.advancedchatcore.util.Color;
import io.github.darkkronicle.advancedchathud.config.ChatTab;

import java.util.List;

public class ChatTabObject extends KonstructObject<ChatTabObject> {

    private static abstract class ColorFunction implements ObjectFunction<ChatTabObject> {
        @Override
        public Result parse(ParseContext context, ChatTabObject self, List<Node> input) {
            Result r1 = Function.parseArgument(context, input, 0);
            if (Function.shouldReturn(r1)) return r1;
            IntegerObject c1 = new IntegerObject(IntegerObject.fromObject(r1.getContent()).orElse(0));

            Result r2 = Function.parseArgument(context, input, 1);
            if (Function.shouldReturn(r2)) return r2;
            IntegerObject c2 = new IntegerObject(IntegerObject.fromObject(r2.getContent()).orElse(0));

            Result r3 = Function.parseArgument(context, input, 2);
            if (Function.shouldReturn(r3)) return r3;
            IntegerObject c3 = new IntegerObject(IntegerObject.fromObject(r3.getContent()).orElse(0));

            Result r4 = Function.parseArgument(context, input, 3);
            if (Function.shouldReturn(r4)) return r4;
            IntegerObject c4 = new IntegerObject(IntegerObject.fromObject(r4.getContent()).orElse(0));

            setColor(self, new Color(c1.getValue(), c2.getValue(), c3.getValue(), c4.getValue()));
            return Result.success((KonstructObject<?>) null);
        }

        @Override
        public IntRange getArgumentCount() {
            return IntRange.of(4);
        }

        public abstract void setColor(ChatTabObject tabObject, Color color);
    }


    private final static List<ObjectFunction<ChatTabObject>> FUNCTIONS = List.of(
            new ColorFunction() {
                @Override
                public void setColor(ChatTabObject tabObj, Color color) {
                    tabObj.tab.setMainColor(color);
                }

                @Override
                public String getName() {
                    return "setMainColor";
                }
            },
            new ColorFunction() {
                @Override
                public void setColor(ChatTabObject tabObj, Color color) {
                    tabObj.tab.setBorderColor(color);
                }

                @Override
                public String getName() {
                    return "setBorderColor";
                }
            },
            new ColorFunction() {
                @Override
                public void setColor(ChatTabObject tabObj, Color color) {
                    tabObj.tab.setInnerColor(color);
                }

                @Override
                public String getName() {
                    return "setInnerColor";
                }
            },
            new ObjectFunction<>() {
                @Override
                public Result parse(ParseContext context, ChatTabObject self, List<Node> input) {
                    Result s1 = Function.parseArgument(context, input, 0);
                    if (Function.shouldReturn(s1)) return s1;
                    self.tab.setAbbreviation(s1.getContent().getString());
                    return Result.success((KonstructObject<?>) null);
                }

                @Override
                public String getName() {
                    return "setAbbreviation";
                }

                @Override
                public IntRange getArgumentCount() {
                    return IntRange.of(1);
                }
            },
            new ObjectFunction<>() {
                @Override
                public Result parse(ParseContext context, ChatTabObject self, List<Node> input) {
                    Result s1 = Function.parseArgument(context, input, 0);
                    if (Function.shouldReturn(s1)) return s1;
                    self.tab.setForward(s1.getContent().getBoolean());
                    return Result.success((KonstructObject<?>) null);
                }

                @Override
                public String getName() {
                    return "setForward";
                }

                @Override
                public IntRange getArgumentCount() {
                    return IntRange.of(1);
                }
            }

    );

    private final CustomChatTab tab;

    public ChatTabObject(CustomChatTab tab) {
        super(FUNCTIONS);
        this.tab = tab;
    }

    @Override
    public String getString() {
        return "chattab-" + tab.toString();
    }

    @Override
    public String getTypeName() {
        return "chattab";
    }

}