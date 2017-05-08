package dlelang.implement;

import dlelang.interfaces.TransaksiInterface;
import dlelang.model.Transaksi;

import java.util.List;

/**
 * Created by Ultrabook on 5/5/2017.
 */
public class TransaksiImplement implements TransaksiInterface {
    @Override
    public int insert(Transaksi transaksi) {
        return 0;
    }

    @Override
    public List<Transaksi> get() {
        return null;
    }

    @Override
    public List<Transaksi> getPemenang() {
        return null;
    }
}
