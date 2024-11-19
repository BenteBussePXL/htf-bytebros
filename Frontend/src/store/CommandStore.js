import { defineStore } from 'pinia'
import axios from 'axios' // Make sure to install axios

export const useCommandStore = defineStore('command', {
    state: () => ({
        name: '',
        error: null
    }),
    actions: {
        async checkDisariumNumber(number) {
            try {
                const response = await axios.post('/command/disarium', { number })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async findPerfectNumber(num, nth) {
            try {
                const response = await axios.post('/command/perfect-number', { num, nth })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async getFirstAndLastDayOfMonth(month, year) {
            try {
                const response = await axios.post('/command/day-of-month', { month, year })
                this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async checkStringInOrder(string) {
            try {
                const response = await axios.post('/command/inorder', { string })
                this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decryptCaesarCipher(shift, text) {
            try {
                const response = await axios.post('/command/caesar', { shift, text })
                this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decodeMorse(morseCode) {
            try {
                const response = await axios.post('/command/morse', { morseCode })
                //this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async convertToHexDec(stringCode) {
            try {
                const response = await axios.post('/command/hexdec', { stringCode })
                this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decodeBase64(encodedString) {
            try {
                const response = await axios.post('/command/base64', { encodedString })
                this.lastResponse = response.data
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        }
    },
    getters: {
        getError: (state) => state.error,
        getName: (state) => state.name
    }
})