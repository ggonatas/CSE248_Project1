package Model;

import java.util.Arrays;
import java.util.List;

public enum StateName {
    AK("Alaska", "AK"), AL("Alabama", "AL"), AR("Arkansas", "AR"), AS("American Samoa", "AS"), AZ("Arizona", "AZ"), CA("California", "CA"),
    CO("Colorado", "CO"), CT("Connecticut", "CT"), DC("District of Columbia", "DC"), DE("Delaware", "DE"), FL("Florida", "FL"), GA("Georgia", "GA"),
    GU("Guam", "GU"), HI("Hawaii", "HI"), IA("Iowa", "IA"), ID("Idaho", "ID"), IL("Illinois", "IL"), IN("Indiana", "IN"), KS("Kansas", "KS"),
    KY("Kentucky", "KY"), LA("Louisiana", "LA"), MA("Massachusetts", "MA"), MD("Maryland", "MD"), ME("Maine", "ME"), MI("Michigan", "MI"),
    MN("Minnesota", "MN"), MO("Missouri", "MO"), MP("Northern Mariana Islands", "MP"), MS("Mississippi", "MS"), MT("Montana", "MT"), NC("North Carolina", "NC"),
    ND("North Dakota", "ND"), NE("Nebraska", "NE"), NH("New Hampshire", "NH"), NJ("New Jersey", "NJ"), NM("New Mexico", "NM"), NV("Nevada", "NV"),
    NY("New York", "NY"), OH("Ohio", "OH"), OK("Oklahoma", "OK"), OR("Oregon", "OR"), PA("Pennsylvania", "PA"), PR("Puerto Rico", "PR"), RI("Rhode Island", "RI"),
    SC("South Carolina", "SC"), SD("South Dakota", "SD"), TN("Tennessee", "TN"), TX("Texas", "TX"), UM("U.S. Minor Outlying Islands", "UM"), UT("Utah", "UT"),
    VA("Virgina", "VA"), VI("Virgin Islands", "VI"), VT("Vermont", "VT"), WA("Washington", "WA"), WI("Wisconsin", "WI"), WV("West Virginia", "WV"), WY("Wyoming", "WY");

    private final List<String> stateName;

    StateName(String ...stateName){
        this.stateName = Arrays.asList(stateName);
    }

    public String getStateName(){
        return this.stateName.get(0);
    }
    public String getStateAbbr(StateName state){ return state.stateName.get(1);
    }

    public static StateName getStateValue(String stateName){
        for(StateName state : StateName.values()){
            if(state.stateName.contains(stateName))
                return state;
        }
        return null;
    }
}
