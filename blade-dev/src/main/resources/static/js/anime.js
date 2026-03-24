function renderCards() {
    grid.innerHTML = '';
    finalAnimeList.forEach(anime => {
        const card = document.createElement('div');
        card.className = 'anime-card';

        // 季节徽章文本
        let seasonLabel = '';
        if (anime.season === '冬') seasonLabel = '❄️ 2026冬季';
        else if (anime.season === '春') seasonLabel = '🌸 2026春季';
        else if (anime.season === '长期') seasonLabel = '📡 连载中';
        else seasonLabel = '🎬 预定';


        card.innerHTML = `
                <div class="card-header">
                    <div class="anime-name">${escapeHtml(anime.name)}</div>
                    <div class="anime-name-jp">${escapeHtml(anime.nameJp || '')}</div>
                </div>
                <div class="card-body">
                    <div class="info-row">
                        <span class="season-badge">${seasonLabel}</span>
                        <span class="tag">TV 动画</span>
                    </div>
                    <div class="desc">${escapeHtml(anime.desc || '追番手帐 · 敬请期待')}</div>
                </div>
            `;
        grid.appendChild(card);
    });
    countSpan.innerText = finalAnimeList.length;
}

/**
 * Karas 20260324
 * 由于${escapeHtml()}的写法无法被模板引擎解析而放弃
 * 参照BlogController.java
 * 其实${}似乎就不可以, 参照BladeTemplate.validateParamChar()
 * 以及这个js里的东西本来在anime.html里的, 因为 card.innerHTML = `<div... 都会导致 Unexpected token '<' 所以挪了出来
 * 结果挪出来反倒可以用${escapeHtml()}了
 */
// 简单防XSS
function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>]/g, function(m) {
        if (m === '&') return '&amp;';
        if (m === '<') return '&lt;';
        if (m === '>') return '&gt;';
        return m;
    }).replace(/[\uD800-\uDBFF][\uDC00-\uDFFF]/g, function(c) {
        return c;
    });
}