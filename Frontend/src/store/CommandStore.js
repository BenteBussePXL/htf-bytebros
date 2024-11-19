import { defineStore } from 'pinia'
import axios from 'axios' // Make sure to install axios
const url = "http://localhost:8080";

export const useCommandStore = defineStore('command', {
    state: () => ({
        name: '',
        error: null
    }),
    actions: {
        async checkDisariumNumber(number) {
            try {
                const response = await axios.post(`${url}/command/disarium`, { number })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async findPerfectNumber(num, nth) {
            try {
                const response = await axios.post(`${url}/command/perfect-number`, { num, nth })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async getFirstAndLastDayOfMonth(month, year) {
            try {
                const response = await axios.post(`${url}/command/day-of-month`, { month, year })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async checkStringInOrder(string) {
            try {
                const response = await axios.post(`${url}/command/inorder`, { string })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decryptCaesarCipher(shift, text) {
            try {
                const response = await axios.post(`${url}/command/caesar`, { shift, text })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decodeMorse(morseCode) {
            try {
                const response = await axios.post(`${url}/command/morse`, { morseCode })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async convertToHexDec(stringCode) {
            try {
                const response = await axios.post(`${url}/command/hexdec`, { stringCode })
                return response.data
            } catch (error) {
                this.error = error
                throw error
            }
        },
        async decodeBase64(encodedString) {
            try {
                const response = await axios.post(`${url}/command/base64`, { encodedString })
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
