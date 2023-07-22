package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ClimateChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

}
