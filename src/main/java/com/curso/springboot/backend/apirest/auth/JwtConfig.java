package com.curso.springboot.backend.apirest.auth;

public class JwtConfig {
	public static String LLAVE_SECRETA = "alguna.clave.secreta.perpowaymam";
	
	public static String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEAx2X4jG30MKHKCZVwE1xN8ChSacjcmAf1qy4qkF5QSlDgdDUN\r\n"
			+ "sL+UH4yzZgYoaxdyM3llXLPHr4Z7bI3cnmMVPDDJ5/Y5KbYHc/TgxXVzaXVJ2prK\r\n"
			+ "v6GICm48+BEPnTom2K2gVfFVdKsMAnAk+DLlX3DaDlllDXwlQ6OMEGEXVzw3QNuE\r\n"
			+ "TFJOYfLzziPfI+ag/fgkCt6m2kWUU8LWNJQ6N2qNoR0Xnsa75p496iqQ7rtBfZX2\r\n"
			+ "0gRpwbVhVHll+VWw1COtiZ+KJ5sK51yVdUzpiE6BSNr6unKnV1w88WGJX5oczcKC\r\n"
			+ "OZzkhn/pOmoZsXhgw6xRjuLZPll2Y6DrIaJhWQIDAQABAoIBAQCsf9Ld6eFukQiv\r\n"
			+ "8c/bb+cc9EUXruTnwTsOtAmui2j6Ya1L2vnVBmemQ5qPkJQ20TnCviqqX/1IJBdy\r\n"
			+ "eed6Pk2InMyPF7usEgWwkxBAwrLtcMJLk84CqkN+go/MKzZO1pf0q4Ah2X4MSE1D\r\n"
			+ "brEM5zr3rjF2aG+7ksLKGRp6sQP4Ol+u7vqDZ/hF/VoYEC66Ve5fpZhXpPvZrvtf\r\n"
			+ "HkkdsFb9u6F80vBXHENNYryDHH4eLI2kRqgeDTM4yh9yDwiZ/M6Dov+rgf9mE36L\r\n"
			+ "WRehxN8S1r2jRFgDI04hJ/GgtHLPjFUFQHhFIp85Y16eiQRtJDlHyuHowdltdL9/\r\n"
			+ "Rj61ZamBAoGBAPOD+m6VIFMzVz2v8NaXxdoLkXH672edRn7WfpQbuFrFwXjRwgJn\r\n"
			+ "cjPuczNRxkCwDVjhGBPTdHFPsmTIgnwK2ErRxvjhniS3JHzQ9+Xtx3KjNHiGD6s2\r\n"
			+ "0NaYGB3fQ71oFzlBeKTNjahvR52g9suYo50XumejNgOa7gnfNZOetn99AoGBANGe\r\n"
			+ "+dJ7HMK7WiYSvP2XRNL8kdypwZKK75nHRivvovVzhum2vISbzVmUqNjvxICHT0Ah\r\n"
			+ "32XFyjr0tvvXbmUayaaYHnzj0RZd61FxbHBEGLIjGQVIujW5nsaBG6bQ7IvCV8Ys\r\n"
			+ "W8r5bglbTI9bSQdE5cj/6TNXVtpo35JIgJdJiggNAoGBAIOognjRRcXWB0AB5PuQ\r\n"
			+ "2VbXVE7EtjVWEiw3ef5HsnfZXY4eEn/MQDGVpVFE0CRfSwgXwzXM6LLuvOvCgNHh\r\n"
			+ "5ykknCgpi6AcKK0XKkB1O5VeadsT2Frgay7q0ncsI+q+4XeCT2EDBajgaV7vDR/s\r\n"
			+ "qeEgWBb6ZhSy6ioKNWRXtiHdAoGAINEWo1EKy6GxF0zPsZPA/pbz7uvUljCSGi0M\r\n"
			+ "W0yE4pBXirdFBC2wFFolpPDEfqqwSYYEB1bTti8hhoPPkJyE/UPLYhn4XdwOPw3W\r\n"
			+ "wCypARpljGI6ZLbOmJ0VyyPiJG8kdq3zIO+zvltAvSC5ah3rK3CE/biqrD7G4Yvg\r\n"
			+ "Nc8ZgPECgYEAmxRNtnd7KeK7yLe/C+fTYPeiaukEm51sadZfTa6j29iFNHXEyXdv\r\n"
			+ "0XmYw2K7hlzYpAiwyFlaNA23YIJtWLAKbjZyk5Gdy5+zQLL/4adjVTAyGH2kF6Ma\r\n"
			+ "y3FRPMvXJoPXoUKdZjtRQOHO9e/rGbQTp8FRsDVQnaHN0aWskMwCPRA=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx2X4jG30MKHKCZVwE1xN\r\n"
			+ "8ChSacjcmAf1qy4qkF5QSlDgdDUNsL+UH4yzZgYoaxdyM3llXLPHr4Z7bI3cnmMV\r\n"
			+ "PDDJ5/Y5KbYHc/TgxXVzaXVJ2prKv6GICm48+BEPnTom2K2gVfFVdKsMAnAk+DLl\r\n"
			+ "X3DaDlllDXwlQ6OMEGEXVzw3QNuETFJOYfLzziPfI+ag/fgkCt6m2kWUU8LWNJQ6\r\n"
			+ "N2qNoR0Xnsa75p496iqQ7rtBfZX20gRpwbVhVHll+VWw1COtiZ+KJ5sK51yVdUzp\r\n"
			+ "iE6BSNr6unKnV1w88WGJX5oczcKCOZzkhn/pOmoZsXhgw6xRjuLZPll2Y6DrIaJh\r\n"
			+ "WQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
