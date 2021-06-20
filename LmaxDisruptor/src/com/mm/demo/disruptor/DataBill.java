package com.mm.demo.disruptor;

import com.lmax.disruptor.EventFactory;

import javax.swing.*;
import java.util.Date;

class Product
{
    private String name;
    private int price;
}
public class DataBill {

    private Product[] products;
    private int discount;
    private Date time;

    public void Set(Product[] products,int discount, Date time){
        this.products = products;
        this.discount = discount;
        this.time = time;
    }
    public Product[] GetProducts(){
        return products;
    }
    public int GetDiscount(){
        return discount;
    }
    public Date getTime(){
        return time;
    }

    public final static EventFactory<DataBill> EVENT_FACTORY = new EventFactory<DataBill>() {
        public DataBill newInstance() {
            return new DataBill();
        }
    };
}
