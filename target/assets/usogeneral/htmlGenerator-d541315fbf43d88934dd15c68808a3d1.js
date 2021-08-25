function Field(e,t,n,r,i,s,o,u){this.id=e;this.name=t;this.type=n;this.checked=s;this.value=o;this.validations=r;this.options=i;this.parent=u}function Validation(e,t,n){this.type=e;this.value=t;this.active=n}function OptionList(e,t,n,r){this.id=e;this.name=t;this.value=n;this.label=r}function Parent(e,t,n,r){this.id=e;this.name=t;this.value=n;this.label=r}function getTextField(e){var t=document.createElement("div");var n=document.createElement("input");var r=document.createElement("label");if(e["name"]!=null){r.innerHTML=e["name"]}n=addAttributes(n,e);t.appendChild(r);t.appendChild(n);return t}function getSelectField(e){var t=document.createElement("div");var n=document.createElement("select");var r=document.createElement("label");if(e["name"]!=null){r.innerHTML=e["name"]}n=addAttributes(n,e);e["options"].forEach(function(e){var t=document.createElement("option");t.setAttribute("id",e["id"]);t.setAttribute("value",e["value"]);t.setAttribute("name",e["name"]);t.innerHTML=e["name"];n.add(t)});t.appendChild(r);t.appendChild(n);return t}function getTextArea(e){var t=document.createElement("div");var n=document.createElement("textarea");var r=document.createElement("label");if(e["name"]!=null){r.innerHTML=e["name"]}n=addAttributes(n,e);t.appendChild(r);t.appendChild(n);return t}function getCheckBox(e){var t=document.createElement("div");var n=document.createElement("input");n.setAttribute("type","checkbox");var r=document.createElement("label");if(e["name"]!=null){r.innerHTML=e["name"]}if(e["checked"]==true){n.checked=true}n=addAttributes(n,e);t.appendChild(r);t.appendChild(n);return t}function getRadioGroup(e){var t=document.createElement("div");var n=document.createElement("label");var r=document.createElement("div");if(e["name"]!=null){n.innerHTML=e["name"]}t.appendChild(n);t.innerHTML=t.innerHTML+"<br>";e["options"].forEach(function(t){r.setAttribute("class","custom-control-input");var n=document.createElement("input");n.setAttribute("type","radio");n.setAttribute("id",t["id"]);n.setAttribute("value",t["value"]);n.setAttribute("name",e["name"]);r.appendChild(n);r.innerHTML=r.innerHTML+t["name"]+"<br>"});t.appendChild(r);return t}function addAttributes(e,t){e.setAttribute("id",t["id"]);var n=false;if(t["value"]!=null){e.setAttribute("value",t["value"])}if(t["validations"]!=null){t["validations"].forEach(function(e){if(e["type"]=="num"){if(e["active"]==true){n=true}}});t["validations"].forEach(function(t){if(t["type"]=="req"){if(t["active"]==true){e.setAttribute("required",true)}}if(t["type"]=="num"){if(t["active"]==true){e.setAttribute("type","number")}}if(t["type"]=="max"){if(t["active"]==true){if(n){var r=1;for(var i=0;i<t["value"];i++){r=r*10}if(t["value"]==0)r=0;e.setAttribute("max",r-1)}else{e.setAttribute("maxlength",t["value"])}}}if(t["type"]=="min"){if(t["active"]==true){if(n){var s=1;for(var i=1;i<t["value"];i++){s=s*10}if(t["value"]==0)s=0;e.setAttribute("min",s)}else{e.setAttribute("pattern",".{"+t["value"]+",}")}}}})}return e}function getField(e){if(e["type"]==1){return getTextField(e)}else if(e["type"]==2){return getSelectField(e)}else if(e["type"]==3){return getTextArea(e)}else if(e["type"]==4){return getCheckBox(e)}else if(e["type"]==5){return getRadioGroup(e)}}