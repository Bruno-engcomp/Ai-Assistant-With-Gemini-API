package org.example.intelligentapiwithspringai.infrastructure.tts;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

@Service
public class GoogleCloudTextToSpeechAdapter {

    public byte[] synthesizeSpeech(String text) {
        // O try-with-resources garante que o cliente seja fechado corretamente
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

            // 1. Define o texto de entrada
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // 2. Define a voz (Idioma e Modelo)
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("pt-BR")
                    .setName("pt-BR-Neural2-B") // Modelo neural masculino/feminino
                    .build();

            // 3. Define o formato de saída (MP3)
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // 4. Faz a requisição para a API do Google Cloud
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // 5. Retorna o conteúdo de áudio em bytes
            ByteString audioContents = response.getAudioContent();
            return audioContents.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar áudio com o Google Cloud TTS", e);
        }
    }
}