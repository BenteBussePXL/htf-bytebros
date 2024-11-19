package be.pxl.bytebros.backend.controller;

import be.pxl.bytebros.backend.controller.data.*;
import be.pxl.bytebros.backend.service.AlienEncService;
import be.pxl.bytebros.backend.service.HabConService;
import be.pxl.bytebros.backend.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/command")
@CrossOrigin(origins = "*")
public class    TerminalController {
    private final AlienEncService alienEncService;
    private final HabConService habConService;
    private final NavService navService;

    @Autowired
    public TerminalController(AlienEncService alienEncService, HabConService habConService, NavService navService) {
        this.alienEncService = alienEncService;
        this.habConService = habConService;
        this.navService = navService;
    }

    @PostMapping("/disarium")
    public ResponseEntity<?> isDisariumNumber(@RequestBody DisariumNumberRequest disariumNumberRequest){
        try {
            String output = habConService.isNumberADisariumNumberYesOrNo(disariumNumberRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/perfect-number")
    public ResponseEntity<?> perfectNumberFinder(@RequestBody PerfectNumberRequest perfectNumberRequest){
        try {
            int perfectNumber = habConService.nthPerfectNumber(perfectNumberRequest);
            return new ResponseEntity<>(perfectNumber, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/day-of-month")
    public ResponseEntity<?> getFirstAndLastDayOfMonth(@RequestBody FirstAndLastDayOfMonthRequest firstAndLastDayOfMonthRequest){
        try {
            String output = navService.getFirstAndLastDayOfMonth(firstAndLastDayOfMonthRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/inorder")
    public ResponseEntity<?> isStringInOrder(@RequestBody IsStringInOrderRequest isStringInOrderRequest){
        try {
            String output = navService.IsStringInOrder(isStringInOrderRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/caesar")
    public ResponseEntity<?> decryptCaesarCypher(@RequestBody DecryptCaesarCipherRequest decryptCaesarCipherRequest){
        try {
            String output = navService.decryptCaesarCipher(decryptCaesarCipherRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/morse")
    public ResponseEntity<?> decodeMorse(@RequestBody DecodeMorseRequest decodeMorseRequest){
        try {
            String output = alienEncService.decodeMorse(decodeMorseRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/hexdec")
    public ResponseEntity<?> convertStringToHexDec(@RequestBody ConvertStringToHexDecRequest convertStringToHexDecRequest){
        try {
            String output = alienEncService.convertStringToHexDec(convertStringToHexDecRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/base64")
    public ResponseEntity<?> decodeBase64ToString(@RequestBody DecodeBase64ToStringRequest decodeBase64ToStringRequest){
        try {
            String output = alienEncService.decodeBase64ToString(decodeBase64ToStringRequest);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
