package com.freightfox.pdfgenerator.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String name;
	private String quantity;
	private double rate;
	private double amount;
}
