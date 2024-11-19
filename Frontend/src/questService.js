import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'https://htf.collide.be',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Helper function to wait for a given time
const wait = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export default {
  async bruteForceQuest() {

    let endpoint = '/use/3923f09b-8bc2-4147-9c50-81ec8be12b95';

    for (let passcode = 1458; passcode <= 9999; passcode++) {

      const paddedPasscode = passcode.toString().padStart(4, '0'); 
      try {
       
        const response = await apiClient.post(endpoint, null,{
          params: {
            result: paddedPasscode,  
          },
        });

        // Check if the passcode is correct
        if (response.data.solved) {
          console.log(`Passcode found: ${paddedPasscode}`);
          return { passcode: paddedPasscode, ...response.data };
        }
      } catch (err) {
        // Handle invalid attempt errors or stop conditions
        if (err.response && err.response.status === 400) {
          console.log(`Passcode ${paddedPasscode} is incorrect.`);
        } else {
          console.error('Unexpected error:', err);
          throw err;
        }
      }

 
    }

    console.error('Could not brute force the passcode.');
    return null;
  },
};
