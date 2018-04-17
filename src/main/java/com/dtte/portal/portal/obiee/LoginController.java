/*******************************************************************************
 // Copyright (c) Microsoft Corporation.
 // All rights reserved.
 //
 // This code is licensed under the MIT License.
 //
 // Permission is hereby granted, free of charge, to any person obtaining a copy
 // of this software and associated documentation files(the "Software"), to deal
 // in the Software without restriction, including without limitation the rights
 // to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
 // copies of the Software, and to permit persons to whom the Software is
 // furnished to do so, subject to the following conditions :
 //
 // The above copyright notice and this permission notice shall be included in
 // all copies or substantial portions of the Software.
 //
 // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 // IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 // FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
 // AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 // LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 // OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 // THE SOFTWARE.
 ******************************************************************************/
package com.dtte.portal.portal.obiee;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import com.microsoft.aad.adal4j.AuthenticationResult;

public class LoginController  extends HttpServlet{
	
	/**
	 * Default serialVersion
	 */
	private static final long serialVersionUID = 1L;
	@Override 
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws IOException,ServletException{
	    this.doPost(request,response);
	}
	@Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession();
        AuthenticationResult result = (AuthenticationResult) session.getAttribute(AuthHelper.PRINCIPAL_SESSION_NAME);
        if (result == null) {
            request.setAttribute("error", new Exception("AuthenticationResult not found in session."));
            response.sendRedirect("error.jsp");
        } else {
            String data;
            try {
                String tenant = session.getServletContext().getInitParameter("tenant");
                data = getRolFromGraph(result, tenant);
                request.setAttribute("rol", data);
            } catch (Exception e) {
            	request.setAttribute("error", e);
            	request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("/reportes/home.jsp").forward(request, response);
    }

   private String getRolFromGraph(AuthenticationResult result, String tenant) throws Exception {
    URL url = new URL(null,String.format("https://graph.windows.net/%s/users/%s/memberOf?api-version=1.6", 
    		tenant,
    		result.getUserInfo().getUniqueId()),
            new sun.net.www.protocol.https.Handler());

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // Set the appropriate header fields in the request header.
    conn.setRequestProperty("api-version", "1.6");
    conn.setRequestProperty("Authorization", result.getAccessToken());
    conn.setRequestProperty("Accept", "application/json;odata=minimalmetadata");
    String goodRespStr = HttpClientHelper.getResponseStringFromConn(conn, true);
    // logger.info("goodRespStr ->" + goodRespStr);
    int responseCode = conn.getResponseCode();
    JSONObject response = HttpClientHelper.processGoodRespStr(responseCode, goodRespStr);
    JSONArray groups = new JSONArray();;
    //User user = new User();
    //JSONObject JSONuser = JSONHelper.fetchDirectoryObjectJSONObject(response);
    //JSONHelper.convertJSONObjectToDirectoryObject(JSONuser, user);
    //return user.jobTitle;
    
    groups = JSONHelper.fetchDirectoryObjectJSONArray(response);
    System.out.println(groups.toString());
    StringBuilder builder = new StringBuilder();
    
    for (int i = 0; i < groups.length(); i++) {
        JSONObject thisGroupJSONObject = groups.optJSONObject(i);
        //user = new User();
        //JSONHelper.convertJSONObjectToDirectoryObject(thisGroupJSONObject, user);
        builder.append(thisGroupJSONObject.getString("displayName")+ "<br/>");
    }
    return builder.toString();
   }
}

