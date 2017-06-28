package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
    private final String name;
    private final Integer id;
    private final List<Individual> members;
    
    public Team(Map<String, Object> teamMap){
       this.name  =  (String)teamMap.get("name");
       	this.id    =  (Integer)teamMap.get("id");
   
       	this.members=(List<Individual>) teamMap.get("members");
    }
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     */
    public List<Individual> getActiveMembers(){
      List<Individual> individual;
    	List<Individual> ActiveMembers = new ArrayList<>();
    	individual=this.getMembers();
    	for(Individual member:individual)
    	{
    		if(member.isActive())
    		{
    			ActiveMembers.add(member);
    		}
    	}
    	return ActiveMembers;
    }
        
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
         List<Individual> individual;
    	List<Individual> InActiveMembers = new ArrayList<>();
    	individual=this.getMembers();
    	for(Individual member:individual)
    	{
    		if(!member.isActive())
    		{
    			InActiveMembers.add(member);
    		}
    	}
    	return InActiveMembers;
       
    }
}
