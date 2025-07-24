package org.elis.gardener.logic;

import android.location.Location;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.ai.FirebaseAI;
import com.google.firebase.ai.GenerativeModel;
import com.google.firebase.ai.java.GenerativeModelFutures;
import com.google.firebase.ai.type.Content;
import com.google.firebase.ai.type.GenerateContentResponse;
import com.google.firebase.ai.type.GenerationConfig;
import com.google.firebase.ai.type.GenerativeBackend;
import com.google.firebase.ai.type.Schema;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GeminiHelper {

    public void fetchPlantData(Location location, FutureCallback<GenerateContentResponse> callback) {
        Schema jsonSchema = Schema.obj(
                /* properties */
                Map.of(
                        "plants", Schema.array(
                                /* items */ Schema.obj(
                                        /* properties */
                                        Map.of(
                                                "name", Schema.str(),
                                                "type", Schema.enumeration(List.of("Fiori", "Ortaggi", "Frutti", "Spezie")),
                                                "warnings", Schema.str(),
                                                "suggestions", Schema.str(),
                                                "cureCheckList", Schema.str(),
                                                "plantingPeriod", Schema.str(),
                                                "waterNeedsScale", Schema.enumeration(List.of("Poca", "Media", "Molta")),
                                                "cureNeedsScale", Schema.enumeration(List.of("Poca", "Media", "Molta")),
                                                "fragilityScale", Schema.enumeration(List.of("Poca", "Media", "Molta")),
                                                "grownEasynessScale", Schema.enumeration(List.of("Facile", "Medio", "Difficile"))
                                        )
                                )
                        ),
                        "locationCity", Schema.str(),
                        "locationRegion", Schema.str(),
                        "locationNation", Schema.str()
                ), List.of()
        );
        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.responseMimeType = "application/json";
        configBuilder.responseSchema = jsonSchema;

        GenerationConfig generationConfig = configBuilder.build();
        GenerativeModel ai = FirebaseAI.getInstance(GenerativeBackend.googleAI())
                .generativeModel(
                        /* modelName */ "gemini-2.5-flash",
                        /* generationConfig */ generationConfig);
        GenerativeModelFutures model = GenerativeModelFutures.from(ai);

        Content content = new Content.Builder()
//                .addText("I'm located at 53.27317991792171, -9.044253079624552 at 5 meters above sea level, i need to know wich plants i can grow today, return 20 seasonal plant that can grow at my location")
                .addText("Mi trovo a latitudine: "+location.getLatitude()+", longitudine: "+location.getLongitude()+", altitudine "+location.getAltitude()+", vorrei sapere quali piante posso crescere nella zona in cui mi trovo oggi. Ritorna almeno 5 piante che siano di stagione per dove mi trovo, ritorna i dati nella lingua italiana.")
                .build();

        Executor executor = Executors.newSingleThreadExecutor();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(
                response,
                callback,
                executor);
    }
}
