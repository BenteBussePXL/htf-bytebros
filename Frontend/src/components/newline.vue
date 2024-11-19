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
        <input
          type="text"
          id="inputField"
          class="input-field"
          v-model="inputValue"
          @keydown.enter="onEnterPress"
        />
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
      - callBackup: Calls the backup API
      - calcMorse: Calculates the morse code
    `,
    status: "System is operational. No issues detected.",
    exit: "Exiting the shell... Goodbye!",
    clear: null, 
    callBackup: ' ', 
    calcMorse: ' ',
  };
  
  const runMultiLineCommand = async (lines) => {
    isInputVisible.value = false;
  

  
    for (let i = 0; i < lines.length; i++) {
      await new Promise(resolve => setTimeout(resolve, 1000)); 
      commands.value.unshift({
        text: lines[i].text,
        output: lines[i].output
      });
    }
  

    isInputVisible.value = true;
  };
  
  const onEnterPress = async () => {
   
    const enteredCommand = inputValue.value.trim();
    if (!enteredCommand) return;

    const label = 'hack the future shell $: ';
  

    commands.value.unshift({
        text: `${label} ${enteredCommand}`,
        output: commandOutputs[enteredCommand] || "Unknown command. Type 'help' for a list of commands.",
      });

    if (enteredCommand === 'callBackup') {
      try {
        const backupLines = [
          { text: '', output: "Initializing backup..." },
          { text: '', output: "Running backup process..." }, // No label for subsequent lines
          { text: '', output: "Backup completed successfully!" }
        ];
        await runMultiLineCommand(backupLines);
      } catch (error) {
        const backupLines = [
          { text: `${label} callBackup`, output: "Error occurred while starting the backup." },
          { text: '', output: `Error details: ${error.message}` }
        ];
        await runMultiLineCommand(backupLines);
      }
    }else if (enteredCommand.includes('calcMorse')){
        let getValue = enteredCommand.split(' ');
       console.log(store.decodeMorse(getValue[1]));

    }
  
    // Check for other commands
    else if (enteredCommand === 'clear') {
      commands.value = []; // Clear all outputs
    } else {
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
    flex-direction: column-reverse; /* Display the input at the bottom */
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
    background-color: transparent; /* Make the background transparent */
    border: none;
    color: grey;
    flex: 1;
  }
  
  .input-field:focus {
    outline: none;
  }
  
  .command-extra {
    white-space: pre-line; /* Preserve newlines in the command output */

  }


 *{
  font-family: "VT323", monospace;
  font-weight: 400;
  font-style: normal;
}
  </style>
  