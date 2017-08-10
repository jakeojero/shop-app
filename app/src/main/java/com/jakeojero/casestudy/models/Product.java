package com.jakeojero.casestudy.models;

import java.util.ArrayList;

/**
 * Created by Jake Ojero on 2017-08-09.
 */

public class Product {
    private String mId;
    private int mBrandId;
    private String mProductName;
    private String mGraphicName;
    private double mCostPrice;
    private double mMSRP;
    private int mQtyOnHand;
    private int mQtyOnBackOrder;
    private String mDescription;

    public Product(String mId, int mBrandId, String mProductName, String mGraphicName, double mCostPrice, double mMSRP, int mQtyOnHand, int mQtyOnBackOrder, String mDescription) {
        this.mId = mId;
        this.mBrandId = mBrandId;
        this.mProductName = mProductName;
        this.mGraphicName = mGraphicName;
        this.mCostPrice = mCostPrice;
        this.mMSRP = mMSRP;
        this.mQtyOnHand = mQtyOnHand;
        this.mQtyOnBackOrder = mQtyOnBackOrder;
        this.mDescription = mDescription;
    }

    public String getmId() {
        return mId;
    }

    public int getmBrandId() {
        return mBrandId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmGraphicName() {
        return mGraphicName;
    }

    public double getmCostPrice() {
        return mCostPrice;
    }

    public double getmMSRP() {
        return mMSRP;
    }

    public int getmQtyOnHand() {
        return mQtyOnHand;
    }

    public int getGetmQtyOnBackOrder() {
        return mQtyOnBackOrder;
    }

    public String getmDescription() {
        return mDescription;
    }



}
