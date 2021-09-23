package com.app.unofficial_nhl.helper_classes;

import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.pojos.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaticData {
    public static Map<String, Integer> logosMap = null;

    static {
        Map<String,Integer> aMap = new HashMap<String, Integer>();
        aMap.put("Winnipeg Jets", R.drawable.winnipeg_jets_logo);
        aMap.put("Washington Capitals", R.drawable.washington_capitals_logo);
        aMap.put("Vegas Golden Knights", R.drawable.vegas_golden_knights_logo);
        aMap.put("Vancouver Canucks", R.drawable.vancouver_canucks_logo);
        aMap.put("Toronto Maple Leafs", R.drawable.toronto_maple_leafs_logo);
        aMap.put("Tampa Bay Lightning", R.drawable.tampa_bay_lightning_logo);
        aMap.put("St. Louis Blues", R.drawable.st_louis_blues_logo);
        aMap.put("San Jose Sharks", R.drawable.san_jose_sharks_logo);
        aMap.put("Pittsburgh Penguins", R.drawable.pittsburgh_penguins_logo);
        aMap.put("Philadelphia Flyers", R.drawable.philadelphia_flyers_logo);
        aMap.put("Ottawa Senators", R.drawable.ottawa_senators_logo);
        aMap.put("New York Rangers", R.drawable.new_york_rangers_logo);
        aMap.put("New York Islanders", R.drawable.new_york_islanders_logo);
        aMap.put("New Jersey Devils", R.drawable.new_jersey_devils_logo);
        aMap.put("Nashville Predators", R.drawable.nashville_predators_logo);
        aMap.put("Montréal Canadiens", R.drawable.montreal_canadiens_logo);
        aMap.put("Minnesota Wild", R.drawable.minnesota_wild_logo);
        aMap.put("Los Angeles Kings", R.drawable.los_angeles_kings_logo);
        aMap.put("Florida Panthers", R.drawable.florida_panthers_logo);
        aMap.put("Edmonton Oilers", R.drawable.edmonton_oilers_logo);
        aMap.put("Detroit Red Wings", R.drawable.detroit_red_wings_logo);
        aMap.put("Dallas Stars", R.drawable.dallas_stars_logo);
        aMap.put("Columbus Blue Jackets", R.drawable.columbus_blue_jackets_logo);
        aMap.put("Colorado Avalanche", R.drawable.colorado_avalanche_logo);
        aMap.put("Chicago Blackhawks", R.drawable.chicago_blackhawks_logo);
        aMap.put("Carolina Hurricanes", R.drawable.carolina_hurricanes_logo);
        aMap.put("Calgary Flames", R.drawable.calgary_flames_logo);
        aMap.put("Buffalo Sabres", R.drawable.buffalo_sabres_logo);
        aMap.put("Boston Bruins", R.drawable.boston_bruins_logo);
        aMap.put("Arizona Coyotes", R.drawable.arizona_coyotes_logo);
        aMap.put("Anaheim Ducks", R.drawable.anaheim_ducks_logo);
        aMap.put("Seattle Kraken", R.drawable.seattle_kraken_logo);
        logosMap = Collections.unmodifiableMap(aMap);
    }




}
