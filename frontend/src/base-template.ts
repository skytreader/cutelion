import { html, LitElement, HTMLTemplateResult } from 'lit';

export abstract class BaseTemplate extends LitElement {

    protected abstract pageContent(): HTMLTemplateResult;

    render() {
        return html`
        <div>
            <h1><a href="/">CuteL10N</a></h1>
        </div>
        ${this.pageContent()}
        `;
    }

}