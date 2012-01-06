package org.neo4j.tutorial;

import com.sun.jersey.api.NotFoundException;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;


// YOUR CODE GOES HERE
// SNIPPET_START
@Path("/{character}")
// SNIPPET_END
public class Koan12Plugin
{
    // YOUR CODE GOES HERE
    // SNIPPET_START

    @Path("/homeplanet")
    @GET
    public String findHomePlanetFor(@PathParam("character") String character, @Context GraphDatabaseService db)
    {
        Node characterNode = db.index().forNodes("characters").get("character", character).getSingle();

        if(characterNode != null && characterNode.hasRelationship(DoctorWhoRelationships.COMES_FROM, Direction.OUTGOING)) {
            return (String) characterNode.getSingleRelationship(DoctorWhoRelationships.COMES_FROM, Direction.OUTGOING).getEndNode().getProperty("planet");
        } else {
            throw new NotFoundException(String.format("The specifed character [%s] was not found in the database", character));
        }
    }

    // SNIPPET_END
}
