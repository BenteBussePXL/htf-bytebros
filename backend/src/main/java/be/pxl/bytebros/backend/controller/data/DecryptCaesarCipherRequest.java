package be.pxl.bytebros.backend.controller.data;

public record DecryptCaesarCipherRequest(int shift, String text) {
}
