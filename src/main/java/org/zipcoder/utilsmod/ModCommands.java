package org.zipcoder.utilsmod;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModCommands {


    private static boolean listItemsToFile(File saveFile) {

        System.out.println("Saving item list to: " + saveFile.getAbsolutePath());

        try (FileWriter writer = new FileWriter(saveFile)) {
            for (ResourceLocation id : BuiltInRegistries.ITEM.keySet()) {
                Item item = BuiltInRegistries.ITEM.get(id);
                writer.write(id.toString() + "\n");
            }
            System.out.println("Saved item list to: " + saveFile.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save item list: " + e.getMessage());
        }
        return false;
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Register a simple command: /listitems
        dispatcher.register(Commands.literal("listitems")
                .executes(context -> {

                    File savePath = new File("items_list.txt");
                    if (listItemsToFile(savePath))
                        context.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("Item list saved to: " + savePath.getAbsolutePath()), true);
                    else
                        context.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("Failed to save item list (path: " + savePath.getAbsolutePath() + ")!"), true);
                    return Command.SINGLE_SUCCESS;
                })
        );

//        // Example of a command with an argument: /greet <name>
//        dispatcher.register(Commands.literal("greet")
//                .then(Commands.argument("name", StringArgumentType.string())
//                        .executes(context -> {
//                            String name = StringArgumentType.getString(context, "name");
//                            context.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("Hello, " + name + "!"), false);
//                            return Command.SINGLE_SUCCESS;
//                        })
//                )
//        );

        dispatcher.register(Commands.literal("crash")
                .executes(context -> {
                    throw new NullPointerException("Intentional crash triggered by command.");
//                    System.out.println(10 / 0);
//                    context.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("crash: " + 10 / 0), false);
//                    return Command.SINGLE_SUCCESS;
                })
        );

        dispatcher.register(Commands.literal("crashmemory")
                .executes(context -> {
                    List<int[]> memoryFiller = new ArrayList<>();
                    while (true) {
                        memoryFiller.add(new int[1000000]); // Allocates large arrays continuously
                    }
                })
        );

    }
}