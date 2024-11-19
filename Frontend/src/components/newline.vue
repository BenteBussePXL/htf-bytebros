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
            <label for="inputField" class="input-label">(▀̿Ĺ̯▀̿ ̿) hack the future shell $:</label>
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
    start: 'Wij gaan van start met onze pitch',
    help: `
      Available commands:
      - start: Starts the presentation
      - help: Shows this help message
      - status: Displays the current system status
      - exit: Exits the shell
      - clear: Clears the terminal screen
      - decodeMorse <Code>: Decodes the morse code
      - isDisarium <input>: Checks if input is a disarium
      - perfectnumber <number> <nth>: checks if input is a perfect number
      - getDays <month> <year>: gets the first and last day of the month
      - checkString <string>: checks if the string is ordered alphabetically
    `,
    status: "System is operational. No issues detected.",
    exit: "Exiting the shell... Goodbye!",
    clear: null,
    callBackup: ' ',
    decodeMorse: ' ',
    isDisarium: '',
    perfectNumber: '',
    getDays: '',
    checkString: '',
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

    const label = '(▀̿Ĺ̯▀̿ ̿) hack the future shell $: ';


    if (enteredCommand.includes('decodeMorse')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.decodeMorse(getValue.slice(1).join(' '));


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

    else if (enteredCommand.includes('isDisarium')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.checkDisariumNumber(getValue[1]);

        const backupLines = [
            { text: `${label} ${enteredCommand}`, output: "" },
            { text: '', output: "The code i will be using" },
            {
                text: '', output: `     public String isNumberADisariumNumberYesOrNo(DisariumNumberRequest disariumNumberRequest){
        int num = disariumNumberRequest.number();
        int copy = num, digit, sum = 0;
        String numInString = Integer.toString(num);
        int len = numInString.length();

        while(copy>0)
        {
            digit = copy % 10;
            sum = sum + (int)Math.pow(digit,len);
            len--;
            copy = copy / 10;
        }

        if(sum == num)
            return "Y";
        else
            return "N";
    }
`
            }, { text: '', output: "Running process..." }, // No label for subsequent lines
            { text: '', output: decoded }
        ];

        await runMultiLineCommand(backupLines);
    }

    else if (enteredCommand.includes('getDays')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.getFirstAndLastDayOfMonth(getValue[1], getValue[2]);

        const backupLines = [
            { text: `${label} ${enteredCommand}`, output: "" },
            { text: '', output: "The code i will be using" },
            {
                text: '', output: `     public String getFirstAndLastDayOfMonth(FirstAndLastDayOfMonthRequest firstAndLastDayOfMonthRequest) {
        int year = firstAndLastDayOfMonthRequest.year();
        int month = firstAndLastDayOfMonthRequest.month();


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); 
        Date firstDay = calendar.getTime();

        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, month - 1, lastDay);
        Date lastDayDate = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        String firstDayString = formatter.format(firstDay);
        String lastDayString = formatter.format(lastDayDate);

        return firstDayString.toUpperCase() + "-" + lastDayString.toUpperCase();
    }

`
            }, { text: '', output: "Running process..." }, // No label for subsequent lines
            { text: '', output: decoded }
        ];

        await runMultiLineCommand(backupLines);
    }
    else if (enteredCommand.includes('perfectNumber')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.findPerfectNumber(getValue[1], getValue[2]);

        const backupLines = [
            { text: `${label} ${enteredCommand}`, output: "" },
            { text: '', output: "The code i will be using" },
            {
                text: '', output: `     public int nthPerfectNumber(PerfectNumberRequest perfectNumberRequest) {
        int num = perfectNumberRequest.num();
        int nth = perfectNumberRequest.nth();
        int count = 0;
        while (true) {
            if (isPerfect(num)) {
                count++;
                if (count == nth) {
                    return num;
                }
            }
            num++;
        }
    }
`
            }, { text: '', output: "Running process..." },
            { text: '', output: "The first and last day are" },
            { text: '', output: decoded }
        ];

        await runMultiLineCommand(backupLines);
    }

    else if (enteredCommand.includes('checkString')) {
        let getValue = enteredCommand.split(' ');
        let decoded = await store.checkStringInOrder(getValue[1]);

        const backupLines = [
            { text: `${label} ${enteredCommand}`, output: "" },
            { text: '', output: "The code i will be using" },
            {
                text: '', output: ` public String IsStringInOrder(IsStringInOrderRequest isStringInOrderRequest) {
        String string = isStringInOrderRequest.string();


        char[] charArray = string.toCharArray();
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] < prev) {
                return "N";
            }

            prev = charArray[i];
        }
        return "Y";
    }
`
            }, { text: '', output: "Running process..." },
            { text: '', output: "Is the string in a correct" },
            { text: '', output: decoded }
        ];

        await runMultiLineCommand(backupLines);
    }

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

    margin-top: 5px !important;
}


* {
    font-family: "VT323", monospace;
    font-weight: 400;
    font-style: normal;
}
</style>