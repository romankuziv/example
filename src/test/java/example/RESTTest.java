package example;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import example.entity.Me;
import org.apache.log4j.Logger;
import org.junit.After;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

/**
 * Created by Roman Kuziv on 9/22/2016.
 */
public class RESTTest {
    private static final Logger logger = Logger.getLogger(RESTTest.class);

    private static final String FACEBOOK_API_URL = "https://graph.facebook.com/v2.7/";
    private static final String ACCESS_TOKEN     = "EAACEdEose0cBAJevDg3OU1ZCEvKIhM2iBcKV9iq6fKqRDq69WeEDQ8ANrAdd9mNccu3hsEG2cFVYhbV0jiZC5UW2mwuGw0lrHOluJ5XoYTE9XoOKAa4Y96NaHimknJHBXIu4PWu6jM1ltBuWD3O5NB9BbfB7ygmSMejBoA6wZDZD";

    private Client client;

    @BeforeClass
    public void start() {
        logger.info("start()");
        client = Client.create();
    }

    @After
    public void stop() {
        logger.info("stop()");
        if (client != null) {
            client.destroy();
        }
    }

    @Test
    public void getMe() {
        logger.info("getMe()");
        StringBuilder uri = new StringBuilder(FACEBOOK_API_URL)
                .append("me")
                .append("?access_token=").append(ACCESS_TOKEN);

        WebResource webResource = client
                .resource(uri.toString());

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        int status = response.getStatus();
        logger.info("HTTP Status: " + status);
        Assert.assertEquals(status, 200);

        Me me = response.getEntity(Me.class);
        Assert.assertNotNull(me);

        logger.info(me);

        Assert.assertEquals(me.getId(), "1577499595892499");
        Assert.assertEquals(me.getName(), "Святослав Галицький");
    }
}
