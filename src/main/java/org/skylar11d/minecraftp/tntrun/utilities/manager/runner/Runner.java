package org.skylar11d.minecraftp.tntrun.utilities.manager.runner;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.TitleType;
import org.skylar11d.minecraftp.tntrun.utilities.board.FastBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *  @Author Skylar11D
 */

@FunctionalInterface
public interface Runner {

    Player getPlayer();


    default void prepareBoard(){
        SimpleDateFormat simplifiedDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = simplifiedDate.format(new Date());
        FastBoard fastBoard = new FastBoard(getPlayer());

        fastBoard.updateTitle(Main.C("&c&lTNT&4&lRUN"));
        fastBoard.updateLines(
                Main.C("&7"+date),
                "",
                Main.C("&c&l┃ &fLoses: &c"/*+getLoses()*/),
                Main.C("&c&l┃ &fVictories: &a"/*+getVictories()*/),
                Main.C("&c&l┃ &fAlive players: &c0"),
                Main.C(""),
                Main.C("&7play.example.gg")
        );

    }

    default void spectate(){
        getPlayer().teleport(Main.getInstance().getLocations().of("starting"));

        Bukkit.getServer().getOnlinePlayers().forEach(o -> o.hidePlayer(getPlayer()));
        sendTitle("§c§lDIED!", "§eGood luck in the next game", TitleType.TITLE);
        getPlayer().addPotionEffects(
                Arrays.asList(
                        new PotionEffect(PotionEffectType.NIGHT_VISION, 90*9999, 90*999),
                        new PotionEffect(PotionEffectType.SLOW_DIGGING, 90*9999, 90*999),
                        new PotionEffect(PotionEffectType.INVISIBILITY, 90*9999, 90*999)
                )
        );
        Main.getInstance().getRunnerManager().unregisterRunner(getPlayer());

    }

    default void sendTitle(String title, String subtitle, TitleType type){
        if(title.isEmpty())
            throw new IllegalArgumentException("title cannot be null");

        if(type == TitleType.TITLE){
            getPlayer().sendTitle(title, subtitle);
        }

        if(type == TitleType.SUBTITLE){
            IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\":\""+title+"\"}");
            PacketPlayOutChat packet = new PacketPlayOutChat(icbc, (byte)2);

            ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(packet);
        }

    }

    default boolean checkDatabaseExistence(){
        try {

            Connection connection = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * WHERE UUID=?");
            statement.setString(1, getPlayer().getUniqueId().toString());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                statement.close();
                rs.close();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    default void prepareDataBaseInfo(){

        try {

            Connection conn = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO runners_data (VICTORIES, LOSES, UUID) VALUES (?, ?, ?)"
            );

            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);
            preparedStatement.setString(3, getPlayer().getUniqueId().toString());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){e.printStackTrace();}

        System.out.println(getPlayer().getUniqueId().toString() + " has been inserted to 'runners_data' table");

    }

    default long getVictories(){

        long a = 1L;

        try {

            Connection connection = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT VICTORIES WHERE UUID=?");
            statement.setString(1, getPlayer().getUniqueId().toString());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                a = rs.getLong("VICTORIES");
                rs.close();
                statement.close();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return a;
    }

    default long getLoses(){

        long a = 1L;

        try {

            Connection connection = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT LOSES WHERE UUID=?");
            statement.setString(1, getPlayer().getUniqueId().toString());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                a = rs.getInt("LOSES");
                rs.close();
                statement.close();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return a;
    }

    default void setVictories(int quantity){
        try {
            Connection conn = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement pStatement = conn.prepareStatement("UPDATE runners_data SET 'VICTORIES'=? WHERE UUID=?");
            pStatement.setInt(1, quantity);
            pStatement.setString(2, getPlayer().getUniqueId().toString());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default void setLoses(int quantity){
        try {
            Connection conn = Main.getInstance().getProvider().getMySQL().getConnection();
            PreparedStatement pStatement = conn.prepareStatement("UPDATE runners_data SET 'LOSES'=? WHERE UUID=?");
            pStatement.setInt(1, quantity);
            pStatement.setString(2, getPlayer().getUniqueId().toString());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
