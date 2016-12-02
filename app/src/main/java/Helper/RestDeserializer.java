package Helper;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by marchest on 26.11.2016.
 */
public class RestDeserializer implements JsonDeserializer<ServiceResult> {

    private Type mType;
    private String mKey;

    public RestDeserializer(Type targetType, String key) {
        mType = targetType;
        mKey = key;
    }

    @Override
    public ServiceResult deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject content = json.getAsJsonObject();
        ServiceResult message = new Gson().fromJson(json, ServiceResult.class);
        if (message.getStatusCode() == 200) {
            JsonElement data = content.get(mKey);
            message.setResult(new Gson().fromJson(data, mType));
        }
        return message;
    }
}
