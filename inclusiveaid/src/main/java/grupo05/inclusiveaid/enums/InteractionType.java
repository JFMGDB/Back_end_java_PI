package grupo05.inclusiveaid.enums;

import lombok.Getter;

/**
 * Enumeração que define os tipos de interação possíveis no sistema InclusiveAID.
 * Esta enumeração categoriza as diferentes formas de interação entre o usuário
 * e o sistema, permitindo o registro e análise de interações para melhorar
 * a experiência de uso e acessibilidade.
 */
@Getter
public enum InteractionType {
    /**
     * Interação através de comandos de voz.
     * Usuário controla o sistema através de comandos falados.
     */
    VOICE_COMMAND,

    /**
     * Análise de layout da interface.
     * Sistema analisa a estrutura e elementos da interface.
     */
    LAYOUT_ANALYSIS,

    /**
     * Conversão de texto para fala.
     * Sistema lê conteúdo textual em voz alta.
     */
    TEXT_TO_SPEECH,

    /**
     * Descrição de imagens.
     * Sistema fornece descrições de elementos visuais.
     */
    IMAGE_DESCRIPTION,

    /**
     * Geração de legendas.
     * Sistema cria legendas para conteúdo de áudio.
     */
    SUBTITLE_GENERATION,

    /**
     * Adaptação de layout.
     * Sistema modifica a interface para melhor acessibilidade.
     */
    LAYOUT_ADAPTATION,

    /**
     * Automação de tarefas.
     * Sistema executa tarefas automaticamente.
     */
    TASK_AUTOMATION,

    /**
     * Consciência de contexto.
     * Sistema adapta-se ao contexto de uso.
     */
    CONTEXT_AWARENESS
} 