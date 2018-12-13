package com.wdeanmedical.util;

import java.io.IOException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Base64Encoder {
  BASE64Decoder decoder = new BASE64Decoder();
  BASE64Encoder encoder = new BASE64Encoder();

  public String encode(byte[] bytes) {
    return encoder.encode(bytes);
  }
  public byte[] decode(String encoded) throws IOException {
    return decoder.decodeBuffer(encoded);
  }
}
