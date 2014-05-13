package com.example.model;

public class Gly {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column datastorage..gly.id
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column datastorage..gly.username
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    private String username;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column datastorage..gly.password
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    private String password;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column datastorage..gly.title
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    private String title;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column datastorage..gly.copyright
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    private String copyright;

    
    
    public Gly(Integer id, String username, String password, String title,
			String copyright) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.title = title;
		this.copyright = copyright;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column datastorage..gly.id
     *
     * @return the value of datastorage..gly.id
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column datastorage..gly.id
     *
     * @param id the value for datastorage..gly.id
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column datastorage..gly.username
     *
     * @return the value of datastorage..gly.username
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column datastorage..gly.username
     *
     * @param username the value for datastorage..gly.username
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column datastorage..gly.password
     *
     * @return the value of datastorage..gly.password
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column datastorage..gly.password
     *
     * @param password the value for datastorage..gly.password
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column datastorage..gly.title
     *
     * @return the value of datastorage..gly.title
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column datastorage..gly.title
     *
     * @param title the value for datastorage..gly.title
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column datastorage..gly.copyright
     *
     * @return the value of datastorage..gly.copyright
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column datastorage..gly.copyright
     *
     * @param copyright the value for datastorage..gly.copyright
     *
     * @abatorgenerated Sun Apr 27 20:07:51 CST 2014
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}