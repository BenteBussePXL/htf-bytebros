<template>
    <div>
        <div class="terminal">
            <!-- Display the list of entered commands -->
            <div v-for="(command, index) in commands" :key="index" class="command-output">
                <p>{{ command.text }}</p>
                <p v-if="command.output" class="command-extra">{{ command.output }}</p>
            </div>
        </div>

        <div v-if="isInputVisible" class="labeled-input">
            <label for="inputField" class="input-label">hack the future shell $:</label>
            <input type="text" id="inputField" class="input-field" v-model="inputValue" @keydown.enter="onEnterPress" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useCommandStore } from '../store/CommandStore';

const store = useCommandStore();

const inputValue = ref('');
const commands = ref([]);
const isInputVisible = ref(true);

// Define specific command outputs
const commandOutputs = {
    help: `
      Available commands:
      - help: Shows this help message
      - status: Displays the current system status
      - exit: Exits the shell
      - clear: Clears the terminal screen
      - decodeMorse <Code>: Decodes the morse code
    `,
    status: "System is operational. No issues detected.",
    exit: "Exiting the shell... Goodbye!",
    clear: null,
    callBackup: ' ',
    decodeMorse: ' ',
};

const runMultiLineCommand = async (lines) => {
    isInputVisible.value = false;

    for (let i = 0; i < lines.length; i++) {
        commands.value.unshift({
            text: lines[i].text,
            output: lines[i].output
        });
        await new Promise(resolve => setTimeout(resolve, 1500));
    }


    isInputVisible.value = true;
};

const onEnterPress = async () => {

    const enteredCommand = inputValue.value.trim();
    if (!enteredCommand) return;

    const label = 'hack the future shell $: ';


    if (enteredCommand.includes('decodeMorse')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.decodeMorse(getValue[1]);

        const backupLines = [
            { text: `${label} ${enteredCommand}`, output: "" },
            { text: '', output: "The code i will be using" },
            {
                text: '', output: `    public String decodeMorse(DecodeMorseRequest decodeMorseRequest) {
        String morseCode = decodeMorseRequest.morseCode();
        char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                ',', '.', '?', ' '};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--..", "/"};

        String[] chars = morseCode.split(" ");
        StringBuilder str = new StringBuilder();

        for (String chr : chars) {
            for (int j = 0; j < morse.length; j++) {
                if (morse[j].equals(chr)) {
                    str.append(abc[j]);
                }
            }
        }
        return str.toString().toUpperCase();
    }` 
},
            { text: '', output: "Running process..." }, // No label for subsequent lines
            { text: '', output: decoded }
        ];

        await runMultiLineCommand(backupLines);
    }

    // Check for other commands
    else if (enteredCommand === 'clear') {
        commands.value = []; // Clear all outputs
    } else {
        commands.value.unshift({
            text: `${label} ${enteredCommand}`,
            output: commandOutputs[enteredCommand] || "Unknown command. Type 'help' for a list of commands.",
        });
    }

    inputValue.value = ''; // Clear the input field
};
</script>

<style scoped>
.terminal {
    margin: 50px;
    font-family: monospace;
    font-size: 1.5rem;
    color: white;
    display: flex;
    flex-direction: column-reverse;
    /* Display the input at the bottom */
    gap: 10px;
}

.command-output {
    color: limegreen;
}

.command-extra {
    color: lightblue;
    margin-left: 20px;

}

.labeled-input {
    display: flex;
    align-items: center;
    gap: 10px;
    margin: 50px;
    font-family: monospace;
    font-size: 1.5rem;
    color: grey;
}

.input-field {
    padding: 5px;
    font-size: 1.5rem;
    background-color: transparent;
    /* Make the background transparent */
    border: none;
    color: grey;
    flex: 1;
}

.input-field:focus {
    outline: none;
}

.command-extra {
    white-space: pre-line;
    /* Preserve newlines in the command output */
    margin-top: 5px !important;
}


* {
    font-family: "VT323", monospace;
    font-weight: 400;
    font-style: normal;
}
</style>