/* Copyright 2012 predic8 GmbH, www.predic8.com
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License. */

package com.predic8.policy

import javax.xml.namespace.QName

import com.predic8.policy.creator.PolicyCreator;
import com.predic8.soamodel.*

class Policy extends PolicyOperator{

	/**
	 * ELEMENTNAME will be set at runtime. Depending on the used version, 
	 * it should be the one from the XML document cause it will be used 
	 * to find the end tag of the XML element.
	 */
	QName ELEMENTNAME

	String id
	
	protected def parseAttributes( token,  AbstractParserContext ctx) {
		name = token.getAttributeValue( null , 'Name')
		//From ws-policy spec: /wsp:Policy/(@wsu:Id | @xml:id)
		id = token.getAttributeValue( Consts.WSU_NS, 'Id') ?: token.getAttributeValue( null , 'Id')
	}
	
	protected parseChildren(token, child, AbstractParserContext ctx){
		super.parseChildren(token, child, ctx)
	}
	
	List<String> getAssertions() {
		allPolicyItems.ELEMENTNAME.localPart
	}
	
	void create(PolicyCreator creator, CreatorContext ctx){
		creator.createPolicy(this, ctx)
	}
	
}

