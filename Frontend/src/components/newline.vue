<template>
    <div>
      <div class="terminal">
        <!-- Display the list of entered commands -->
        <div v-for="(command, index) in commands" :key="index" class="command-output">
          <p>{{ command.text }}</p>
          <!-- Show additional output if available -->
          <p v-if="command.output" class="command-extra">{{ command.output }}</p>
        </div>
      </div>
  
      <div class="labeled-input">
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
  
  // Reactive properties
  const inputValue = ref(''); // Current input value
  const commands = ref([]); // List of previous commands
  
  // Define specific command outputs
  const commandOutputs = {
    help: `
      Available commands:
      - help: Shows this help message
  
      - status: Displays the current system status
  
      - exit: Exits the shell
  
      - clear: Clears the terminal screen
    `,
    status: "System is operational. No issues detected.",
    exit: "Exiting the shell... Goodbye!",
    clear: null, 
  };
  
  // Function to handle Enter key press
  const onEnterPress = () => {
    const label = 'hack the future shell $:'; // Label text
  
    // Trim and process the entered command
    const enteredCommand = inputValue.value.trim();
    if (!enteredCommand) return;
  
    // Check for special commands
    if (enteredCommand === 'clear') {
      commands.value = []; // Clear all outputs
    } else {
      // Add the entered command to the list
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
    flex-direction: column-reverse; /* Display the input at the bottom */
    gap: 10px;
  }
  
  .command-output {
    color: limegreen;
  }
  
  .command-extra {
    color: lightblue;
    margin-left: 20px; /* Indent extra command output for clarity */
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
  </style>
  