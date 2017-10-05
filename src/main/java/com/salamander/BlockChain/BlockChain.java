package com.salamander.BlockChain;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

public class BlockChain {

    private byte previousHash;

    private String data;

    private final MessageDigest digest = MessageDigest.getInstance("SHA-256");

    public BlockChain(byte previousHash, String data) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.data = data;
    }

    public byte getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(byte previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            output.write(data.getBytes());
            output.write(previousHash);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] hash = digest.digest(output.toByteArray());

        ByteBuffer bb = ByteBuffer.wrap(hash);
        System.out.println(hash.toString());
        return bb.getInt();
    }
}
