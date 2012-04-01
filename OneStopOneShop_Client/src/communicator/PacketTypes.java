/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communicator;

import java.io.Serializable;

/**
 *
 * @author 090018T
 */
public enum PacketTypes implements Serializable{
    LOGIN,LOGOUT, RESPONSE, ADD_USER, DELETE_USER, UPDATE_PASSWORD,
    CREATE_CUSTOMER,DELETE_CUSTOMER,UPDATE_CUSTOMER, VIEW_CUSTOMER,
    CREATE_ACCOUNT, VIEW_ACCOUNT, UPDATE_ACCOUNT,
    CREATE_LO_CUSTOMER, VIEW_LO_CUSTOMER, UPDATE_LO_CUSTOMER, DELETE_LO_CUSTOMER,
    CREATE_MO_CUSTOMER,VIEW_MO_CUSTOMER, UPDATE_MO_CUSTOMER, DELETE_MO_CUSTOMER,
    CREATE_WALKIN_CUSTOMER, VIEW_WALKIN_CUSTOMER, UPDATE_WALKIN_CUSTOMER, DELETE_WALKIN_CUSTOMER,
    CREATE_SALES_REP, VIEW_SALES_REP, UPDATE_SALES_REP, DELETE_SALES_REP,
    CREATE_TELELPHONE, VIEW_TELELPHONE, UPDATE_TELELPHONE, DELETE_TELELPHONE,
    CREATE_STOCK_ITEM, VIEW_STOCK_ITEM, UPDATE_STOCK_ITEM, DELETE_STOCK_ITEM,
    CREATE_ORDER, VIEW_ORDER, UPDATE_ORDER, DELETE_ORDER,
    CREATE_ORDER_ITEM, VIEW_ORDER_ITEM, UPDATE_ORDER_ITEM, DELETE_ORDER_ITEM,
    GET_CUSTOMER_LIST, GET_ORDER_LIST, GET_STOCK_LIST, GET_SALES_REP_LIST,GET_ITEM_LIST,
    GET_MAIL_ORDER_LIST,GET_LARGE_ORDER_LIST,GET_WALIKIN_LIST;
}