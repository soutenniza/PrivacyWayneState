<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>PrivacyWayne</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet" >
</head>
<body>
<mytags:navbar/>
<mytags:modifybar/>
<ul class="nav nav-justified nav-pills">
    <li class="active">
        <a href="#">Create User</a>
    </li>
    <li class="">
        <a href="/removeuser">Delete User</a>
    </li>
</ul>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;User created!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must fill in all required fields!</b>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form method="POST" action="/submitcreateuser" class="form-horizontal" role="form">
                    <div class="form-group has-error">
                        <div class="col-sm-2">
                            <label path="name" class="control-label">First Name</label>
                        </div>
                        <div class="col-sm-10">
                            <input class="form-control" name="inputFirstName" placeholder="John">
                        </div>
                    </div>
                    <div class="form-group has-error">
                        <div class="col-sm-2">
                            <label class="control-label">Last Name</label>
                        </div>
                        <div class="col-sm-10">
                            <input class="form-control" name="inputLastName" placeholder="Smith">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Age</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputAge" class="form-control">
                                <option>12</option>
                                <option>13</option>
                                <option>14</option>
                                <option>15</option>
                                <option>16</option>
                                <option>17</option>
                                <option>18</option>
                                <option>19</option>
                                <option>20</option>
                                <option>21</option>
                                <option>22</option>
                                <option>23</option>
                                <option>24</option>
                                <option>25</option>
                                <option>26</option>
                                <option>27</option>
                                <option>28</option>
                                <option>29</option>
                                <option>30</option>
                                <option>31</option>
                                <option>32</option>
                                <option>33</option>
                                <option>34</option>
                                <option>35</option>
                                <option>36</option>
                                <option>37</option>
                                <option>38</option>
                                <option>39</option>
                                <option>40</option>
                                <option>41</option>
                                <option>42</option>
                                <option>43</option>
                                <option>44</option>
                                <option>45</option>
                                <option>46</option>
                                <option>47</option>
                                <option>48</option>
                                <option>49</option>
                                <option>50</option>
                                <option>51</option>
                                <option>52</option>
                                <option>53</option>
                                <option>54</option>
                                <option>55</option>
                                <option>56</option>
                                <option>57</option>
                                <option>58</option>
                                <option>59</option>
                                <option>50</option>
                                <option>61</option>
                                <option>62</option>
                                <option>63</option>
                                <option>64</option>
                                <option>65</option>
                                <option>66</option>
                                <option>67</option>
                                <option>68</option>
                                <option>69</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = inputAgeP class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = inputAgeV class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = inputAgeS class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Gender</label>
                        </div>
                        <div class="col-sm-10">
                            <select name = "inputGender" class="form-control">
                                <option>Male</option>
                                <option>Female</option>
                                <option>Not Specified</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = "inputGenderP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = "inputGenderV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name = "inputGenderS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Location</label>
                        </div>
                        <div class="col-sm-10">
                            <input class="form-control" name="inputLocation" placeholder="Enter City">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputLocationP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputLocationV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputLocationS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Political View</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPolitical" class="form-control">
                                <option>Extreme Right</option>
                                <option>Far Right</option>
                                <option>Right</option>
                                <option>Center Right</option>
                                <option>Center</option>
                                <option>Center Left</option>
                                <option>Left</option>
                                <option>Far Left</option>
                                <option>Extreme Left</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPoliticalP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPoliticalV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPoliticalS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Work</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputWork" class="form-control">
                                <option>Unemployed</option>
                                <option>Accountancy, Banking and Finance</option>
                                <option>Charity and Voluntary</option>
                                <option>Energy and Utilities</option>
                                <option>Environment and Agriculture</option>
                                <option>Hospitality</option>
                                <option>Law</option>
                                <option>Leisure, Sport and Tourism</option>
                                <option>Media and Internet</option>
                                <option>Public Services and Admin</option>
                                <option>Retail</option>
                                <option>Science and Pharmaceuticals</option>
                                <option>Teaching and Education</option>
                                <option>Business, Consulting and Management</option>
                                <option>Creative Arts and Design</option>
                                <option>Engineering and Manufacturing</option>
                                <option>Healthcare</option>
                                <option>Information Technology</option>
                                <option>Law Enforcement and Security</option>
                                <option>Marketing, Advertising and PR</option>
                                <option>Property and Construction</option>
                                <option>Recruitment and HR</option>
                                <option>Sales</option>
                                <option>Social Care</option>
                                <option>Transport and Logistics</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputWorkP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputWorkV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputWorkS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Education</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputEducation" class="form-control">
                                <option>None</option>
                                <option>GED</option>
                                <option>Associates</option>
                                <option>4-Year Degree</option>
                                <option>Masters</option>
                                <option>PHD</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputEducationP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputEducationV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputEducationS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Birthday</label>
                        </div>
                        <div class="col-sm-10">
                            <input name="inputBday" type="text" class="form-control" placeholder="MM/DD/YYYY">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputBdayP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputBdayV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputBdayS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Phone</label>
                        </div>
                        <div class="col-sm-10">
                            <input name="inputPhone" type="text" class="form-control" placeholder="XXX-XXX-XXXX">
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPhoneP" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPhoneV" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputPhoneS" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Interest 1</label>
                        </div>
                        <div class="col-sm-10">
                            <input name="inputInterest1" type="text" class="form-control" placeholder="Sports">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputInterest1P" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Visibility Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputInterest1V" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Sensitivity Value</i>
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <select name="inputInterest1S" class="form-control">
                                <option>0</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>
                    <input type="submit" value="submit" class="btn btn-block btn-lg btn-primary"/>
                </form>
                <br><br>
            </div>
        </div>
    </div>
</div>
</body>
</html>
