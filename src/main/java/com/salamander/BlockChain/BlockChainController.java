package com.salamander.BlockChain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlockChainController {

    private BlockChain bc = new BlockChain(new Integer(1234).byteValue(), "first line");

    private Map<Integer, BlockChain> totalBlock;

    public BlockChainController() throws NoSuchAlgorithmException {
        totalBlock = new HashMap<>();
        totalBlock.put(bc.hashCode(), bc);
    }

    @GetMapping
    public String getHashCode() {
        StringBuffer sb = new StringBuffer();
        for(Integer key : totalBlock.keySet()){
            sb.append(key.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    @GetMapping(value = "addBlock")
    public void addBlock(@RequestParam("hash") Integer prevHC) throws NoSuchAlgorithmException {
        BlockChain newBC = new BlockChain(prevHC.byteValue(), "new");
        totalBlock.put(newBC.hashCode(), newBC);
    }
}
