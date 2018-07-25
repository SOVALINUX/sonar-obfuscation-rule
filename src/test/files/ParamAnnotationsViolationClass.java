package company.demo;

/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
@Api(tags = "some", description = " ")
@RestController
@Validated
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/v1/some")
public class SomeRestController {

    @GET// Noncompliant {{PathParam annotation should have explicitly set value}}
    @Path("{providerIndex}")
    public ModelAndView noArguments(@PathParam int providerIndex) {
        //....
    }

    @GET
    @Path("{type}")
    public ModelAndView implicitValue(@PathParam("type") String type) {
        //....
    }

    @POST
    @Path("{providerIndex}/something")
    public ModelAndView explicitValue(@Valid @PathParam(value = "providerIndex") int providerIndex) throws Exception {
        //....
    }

    @Path("{providerIndex}/something")// Noncompliant {{PathParam annotation should have explicitly set value}}
    public ModelAndView otherArgument(@Valid @NotNull @PathParam(required = false) int providerIndex) {
        //....
    }

    @RequestMapping(value = "/explicitValue")
    public ModelAndView explicitValue(@RequestParam(value = "providerIndex") int providerIndex) throws Exception {
        //....
    }

    @RequestMapping(value = "/otherArgument")// Noncompliant {{RequestParam annotation should have explicitly set value}}
    public ModelAndView otherArgument(@RequestParam(required = false) int providerIndex) {
        //....
    }

    @RequestMapping(value = "/implicitQueryValue")
    public ModelAndView explicitValue(@QueryParam("type") String type) throws Exception {
        //....
    }

    @RequestMapping(value = "/queryOtherArgument")// Noncompliant {{QueryParam annotation should have explicitly set value}}
    public ModelAndView otherArgument(@QueryParam(required = false) int providerIndex) {
        //....
    }
    
    @RequestMapping(value = "/explicitName")
    public ModelAndView explicitName(@RequestParam(name = "providerIndex") int providerIndex) throws Exception {
        //....
    }

    @RequestMapping(value = "/explicitName")// Noncompliant {{RequestParam annotation should have explicitly set value}}
    public ModelAndView explicitNameNotSet(@RequestParam(nameParam = "test") int providerIndex) throws Exception {
        //....
    }

}