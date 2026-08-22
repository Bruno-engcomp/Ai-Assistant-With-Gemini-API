package org.example.intelligentapiwithspringai;

import lombok.RequiredArgsConstructor;
import org.example.intelligentapiwithspringai.infrastructure.tts.GoogleCloudTextToSpeechAdapter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.Media;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class ChatModelController {

    private final ChatClient chatClient;
    private final GoogleCloudTextToSpeechAdapter ttsAdapter;

    @PostMapping(value = "/voice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> processVoiceCommand(@RequestParam("audio") MultipartFile audioFile) {
        try {
            Media audioMedia = new Media(
                    MimeTypeUtils.parseMimeType("audio/mp3"),
                    audioFile.getResource()
            );

            UserMessage userMessage = new UserMessage(
                    "Escute o áudio em anexo. Se for um comando de criação de transação ou de busca de transação, execute a ferramenta correspondente. Responda de forma amigável, natural e resumida, adequada para ser lida em voz alta.",
                    List.of(audioMedia)
            );

            // 1. Obtém a resposta em TEXTO do Gemini (após ele chamar as tools)
            String geminiTextResponse = chatClient.prompt()
                    .messages(userMessage)
                    .functions("persistTransactionUseCase", "listTransactionByCategoryUseCase")
                    .call()
                    .content();

            // 2. Converte o texto da resposta em ÁUDIO (MP3) usando o Google TTS
            byte[] audioBytes = ttsAdapter.synthesizeSpeech(geminiTextResponse);

            // 3. Retorna o array de bytes com os headers corretos para download/reprodução
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resposta.mp3\"")
                    .body(audioBytes);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o fluxo de voz", e);
        }
    }
}